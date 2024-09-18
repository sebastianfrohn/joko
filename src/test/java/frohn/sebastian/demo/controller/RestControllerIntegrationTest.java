//package frohn.sebastian.demo.controller;
//
//import frohn.sebastian.demo.DemoApplication;
//import frohn.sebastian.demo.entity.SFEntity;
//import frohn.sebastian.demo.repository.SFEntityRepository;
//import frohn.sebastian.demo.service.SFRestServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { DemoApplication.class, SFRestController.class, SFRestServiceImpl.class, SFEntityRepository.class })
//@WebAppConfiguration
//@DataJpaTest
//class RestControllerIntegrationTest {
//
//    private final static String MSG = "USA FRANCE BRAZIL ITALY CANADA ";
//
//    @Autowired
//    SFRestController sfRestController;
//
//    @Test
//    public void testController() {
//        SFEntity data = sfRestController.getData();
//        String msg = data.getMsg();
//        assertThat(MSG).isEqualToIgnoringCase(msg);
//    }
//
//}