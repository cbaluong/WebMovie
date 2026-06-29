package ra.webmovieapplication.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String description;
    private Boolean gender;
    @Temporal(TemporalType.DATE)
    private Date dob;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
