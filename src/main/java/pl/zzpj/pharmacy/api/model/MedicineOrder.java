package pl.zzpj.pharmacy.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MedicineOrderKey.class)
public class MedicineOrder {

    @EmbeddedId
    private MedicineOrderKey id;

    private int amount;

    @ManyToOne
    @MapsId("medicine_id")
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Order order;

}
