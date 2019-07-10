package com.example.demo;

import com.example.demo.domain.Rezervare;
import com.example.demo.domain.Spectacol;
import com.example.demo.domain.SpectacolData;
import com.example.demo.domain.Spectator;
import com.example.demo.repository.SpectatorRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.RezervareService;
import com.example.demo.service.SpectacolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private SpectacolService service;

	@Autowired
	private AuthService authService;

	@Autowired
	private RezervareService rezervareService;

	@Autowired
	private SpectatorRepository spectatorRepository;


	@Test
	public void testamSpectacolService() {
//		service.addSpectacol(Spectacol.builder()
//				.denumire("denumire1")
//				.descriere("descriere1")
//				.build());
//
//		List<Spectacol> spectacole =  service.getAllSpectacole();
//		assertEquals(spectacole.size(), 1);
//
//
//		service.addSpectacolData(SpectacolData.builder()
//				.spectacolMapat(spectacole.get(0))
//				.pret(99F)
//				.data(new Date())
//				.build());

//		SpectacolData  spectacolData = service.getAllSpectacoleData().get(0);
//		System.out.println(spectacolData);
//		Spectacol spectacol = spectacolData.getSpectacolMapat();
//		System.out.println(spectacol);

//		Spectacol spectacol = service.getAllSpectacole().get(0);
//		Set<SpectacolData> spectacolDatas = spectacol.getSpectacolDatas();
//		spectacolDatas.forEach(System.out::println);

//		service.addSpectacolData(SpectacolData.builder()
//				.spectacolMapat(spectacol)
//				.pret(33F)
//				.data(new Date())
//				.build());
	}
/*
	@Test
	public void testamAuthService(){
		Spectator s = authService.signUpSpectator(Spectator.builder().nume("emy123").password("123").build());

		try {
			authService.loginSpectator(Spectator.builder().nume("emy123").password("13").build());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
*/
	@Test
	public void testamRezervareService(){
/*
		rezervareService.addRezervare(Rezervare.builder()
				.nrScaun(1).pozitieX(1).pozitieY(1)
				.spectacolDataMapat(service.getAllSpectacoleData().get(0))
				.spectatorMapat(spectatorRepository.findAll().get(0))
				.build());

		rezervareService.addRezervare(Rezervare.builder()
				.nrScaun(2).pozitieX(2).pozitieY(2)
				.spectacolDataMapat(service.getAllSpectacoleData().get(0))
				.spectatorMapat(spectatorRepository.findAll().get(1))
				.build());

		rezervareService.addRezervare(Rezervare.builder()
				.nrScaun(1).pozitieX(1).pozitieY(1)
				.spectacolDataMapat(service.getAllSpectacoleData().get(1))
				.spectatorMapat(spectatorRepository.findAll().get(0))
				.build());

		rezervareService.addRezervare(Rezervare.builder()
				.nrScaun(2).pozitieX(2).pozitieY(2)
				.spectacolDataMapat(service.getAllSpectacoleData().get(1))
				.spectatorMapat(spectatorRepository.findAll().get(1))
				.build());
 */

/*
	    rezervareService.getReservationBySpectatorID("emy123").forEach(System.out::println);
		System.out.println("@@@@@@@@@@@@");
		rezervareService.getReservationBySpectatorID("teo123").forEach(System.out::println);
*/

/*
		Date date = new Date();
		date.setTime(1559392766374L);

		Date date2 = new Date();
		date2.setTime(1559392766421L);

		rezervareService.getReservationsBySpectacolID(date, 1L).forEach(System.out::println);
		rezervareService.getReservationsBySpectacolID(date2, 2L).forEach(System.out::println);
 */

		//rezervareService.deleteReservareByID(1L);
//		spectatorRepository.findAll().forEach(x->x.getRezervari().forEach(System.out::println));
	}

}
