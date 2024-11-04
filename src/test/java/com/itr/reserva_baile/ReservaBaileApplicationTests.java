package com.itr.reserva_baile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.itr.reserva_baile.integration.controller.ClaseDeBaileControllerTest;
import com.itr.reserva_baile.integration.controller.EstudioDeBaileControllerTest;
import com.itr.reserva_baile.integration.controller.ReservaControllerTest;
import com.itr.reserva_baile.integration.controller.UsuarioControllerTest;
import com.itr.reserva_baile.unit.repository.ClaseDeBaileRepositoryTest;
import com.itr.reserva_baile.unit.repository.EstudioDeBaileRepositoryTest;
import com.itr.reserva_baile.unit.repository.ReservaRepositoryTest;
import com.itr.reserva_baile.unit.repository.UsuarioRepositoryTest;
import com.itr.reserva_baile.unit.service.ClaseDeBaileServiceTest;
import com.itr.reserva_baile.unit.service.EstudioDeBaileServiceTest;
import com.itr.reserva_baile.unit.service.UsuarioServiceTest;
import com.itr.reserva_baile.unit.service.ReservaServiceTest;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@SpringBootTest
@Suite
@ActiveProfiles("test")
@SuiteDisplayName("Suite de Pruebas Completa")
@SelectClasses({
        // Tests unitarios
        UsuarioServiceTest.class,
        ReservaServiceTest.class,
        ClaseDeBaileServiceTest.class,
        EstudioDeBaileServiceTest.class,

        // Tests de repositorio
        UsuarioRepositoryTest.class,
        ReservaRepositoryTest.class,
        ClaseDeBaileRepositoryTest.class,
        EstudioDeBaileRepositoryTest.class,

        // Tests de integración
        UsuarioControllerTest.class,
        ReservaControllerTest.class,
        ClaseDeBaileControllerTest.class,
        EstudioDeBaileControllerTest.class
})
class ReservaBaileApplicationTests {

    @Test
    void contextLoads() {
    }

}
