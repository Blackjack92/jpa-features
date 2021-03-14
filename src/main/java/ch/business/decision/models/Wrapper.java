package ch.business.decision.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@MappedSuperclass
@RequiredArgsConstructor
public abstract class Wrapper<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    protected T content;
}
