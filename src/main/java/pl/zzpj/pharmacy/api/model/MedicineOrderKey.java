package pl.zzpj.pharmacy.api.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
class MedicineOrderKey implements Serializable {

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "medicine_id")
    Long medicineId;

}