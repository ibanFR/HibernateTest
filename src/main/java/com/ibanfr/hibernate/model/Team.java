package com.ibanfr.hibernate.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "team")
@Entity
public class Team implements Serializable {
    private static final long serialVersionUID = -6197675373768898713L;

    private Long id;

    private String name;


    /**
     * List of members belonging to a Team.
     */
    private List<Person> members;

    /**
     * OneToMany association with {@link Person} entity mapped as a List.
     * <p>
     * Defining {@link CascadeType#ALL} for the associated {@link Person} entity.
     * <p>
     * Defining orphanRemoval to cascade the remove operation to entities that have been removed from the
     * relationship.
     * <p>
     * Using {@link JoinColumn} to implement unidirectional one-to-many association using a foreign key mapping.
     * <p>
     * Specifying {@link ForeignKey} name to avoid using the persistence provider's default foreign key strategy.
     *
     * @return the List of {@link Person} entities.
     */
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "FK_user_team_id"))
    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                                        .append("name", name)
                                        .append("members", members)
                                        .toString();
    }
}