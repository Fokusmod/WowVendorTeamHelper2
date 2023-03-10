package ru.geekbrains.WowVendorTeamHelper.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private WowTeamRegion teamRegion;

}
