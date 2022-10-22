package CaisseDeconnectee.Entities;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="HrPayCashDebt")
public class HrPayCashDebt {
	
	@Transient
    public static final String SEQUENCE_NAME = "PCDebt_sequence";

	@Id
	private long pcd_id;
	
	private LocalDateTime pcd_date;
	
	private int pcd_amount;
	
	private long DEB_ID;
	
	private long CSH_ID ;
}
