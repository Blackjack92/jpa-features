package ch.business.decision.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@MappedSuperclass
@RequiredArgsConstructor
public abstract class Wrapper<T extends IWrapperContent> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    protected T content;
}
