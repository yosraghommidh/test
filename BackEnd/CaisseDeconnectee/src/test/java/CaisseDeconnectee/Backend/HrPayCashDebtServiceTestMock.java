package CaisseDeconnectee.Backend;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import CaisseDeconnectee.Entities.HrPayCashDebt;
import CaisseDeconnectee.Repository.HrPayCashDebtRepository;
import CaisseDeconnectee.Service.HrPayCashDebtService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class HrPayCashDebtServiceTestMock {

	@Mock
	HrPayCashDebtRepository rep;

	@InjectMocks
	HrPayCashDebtService serv;
	
    List<HrPayCashDebt> List = new ArrayList<HrPayCashDebt>() {
    	{add(HrPayCashDebt.builder().pcd_amount(100).build()); } } ;
    

    @Test
    public void testRetrieveAllStock() {
        Mockito.when(rep.findAll()).thenReturn(List);
        List<HrPayCashDebt> L = serv.retirerAll();
        Assertions.assertNotNull(L);
        System.out.println(L.isEmpty());
        System.out.println(List);

        System.out.println(" Retrieve all is working ");
               
    }
}
