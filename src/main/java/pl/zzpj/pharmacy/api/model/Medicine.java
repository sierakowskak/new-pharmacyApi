package pl.zzpj.pharmacy.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private boolean is_prescript;

    @ManyToMany(mappedBy = "medicines")
    private Set<Prescription> prescriptions;

    @OneToMany(mappedBy = "medicine")
    private Set<MedicineOrder> medicineOrders;
}
