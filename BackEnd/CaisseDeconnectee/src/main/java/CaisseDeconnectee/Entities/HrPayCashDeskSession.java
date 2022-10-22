package CaisseDeconnectee.Entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="HrPayCashDeskSession")
public class HrPayCashDeskSession {
	
	@Transient
    public static final String SEQUENCE_NAME = "PCDeskS_sequence";

	@Id
	private long css_id;
	
	@DateTimeFormat(style="yyyy-MM-dd HH-mm")
	private LocalDateTime css_startdt;
	
	@DateTimeFormat(style="yyyy-MM-dd HH-mm")
	private LocalDateTime css_enddt;
	
	private HrPayCashDesk CAH_ID ; //caisse
	
	private long AGEID ; //agent
	
	private long mnt_initial ;
}
