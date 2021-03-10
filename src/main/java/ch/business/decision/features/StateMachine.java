package ch.business.decision.features;

import ch.business.decision.models.Person;
import ch.business.decision.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

@AllArgsConstructor
@Component
public class StateMachine {

    private final PersonRepository repository;

    static class Context {
        Person person;
        int balanceIncrease;

        State currentState = State.INIT;
        Queue<State> history = new LinkedList<>();

        protected void nextState(State nextState) {
            this.history.add(nextState);
            this.currentState = nextState;
        }

    }

    public enum State { INIT, INPUT_DATA_IS_VALID, PERSON_EXISTS, PERSON_DOES_NOT_EXIST, PERSON_CREATED, BALANCE_UPDATED, FAILURE, FINISHED }

    @Transactional
    public Queue<State> run(Person person, int balanceIncrease) {

        // Simple StateMachine
        Context context = new Context();
        context.person = person;
        context.balanceIncrease = balanceIncrease;


        while(context.currentState != State.FINISHED && context.currentState != State.FAILURE) {
            switch (context.currentState) {
                case INIT:
                    context.nextState(this.validateInputData(context));
                    break;
                case INPUT_DATA_IS_VALID:
                    context.nextState(this.findPerson(context));
                    break;
                case PERSON_DOES_NOT_EXIST:
                    context.nextState(this.storePerson(context));
                    break;
                case PERSON_EXISTS:
                    context.nextState(this.updateBalance(context));
                    break;
                case PERSON_CREATED:
                case BALANCE_UPDATED:
                    context.nextState(State.FINISHED);
                    break;
                default:
                    context.nextState(State.FAILURE);
                    break;

            }
        }

        return context.history;
    }

    private State validateInputData(Context context) {
        final Person person = context.person;
        final int deltaBalance = context.balanceIncrease;

        if (person == null || !person.isValid() || deltaBalance <= 0) {
            return State.FAILURE;
        }

        return State.INPUT_DATA_IS_VALID;
    }

    private State findPerson(Context context) {
        final Person person = context.person;
        Optional<Person> personOptional = this.repository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());

        if (personOptional.isPresent()) {
            context.person = personOptional.get();
            return State.PERSON_EXISTS;
        }

        return State.PERSON_DOES_NOT_EXIST;
    }

    private State storePerson(Context context) {
        final Person person = context.person;
        final int balanceIncrease = context.balanceIncrease;
        person.setBalance(balanceIncrease);
        context.person = this.repository.save(person);
        return State.PERSON_CREATED;
    }

    private State updateBalance(Context context) {
        final Person person = context.person;
        final int balanceIncrease = context.balanceIncrease;
        person.setBalance(person.getBalance() + balanceIncrease);
        return State.BALANCE_UPDATED;
    }

}
