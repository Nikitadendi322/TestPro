package com.example.testtesttest.pojo;



import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Lob
    @Column(name = "file", columnDefinition="text")
    private String file;

    @OneToOne(mappedBy = "report", optional = false)
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
