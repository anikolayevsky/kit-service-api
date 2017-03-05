package com.blckly.kit.service.api.controllers;

import com.blckly.kit.service.api.KitServiceApiApplication;
import com.blckly.kit.service.api.model.KitDTO;
import com.blckly.kit.service.api.model.KitOptionDTO;
import com.blckly.kit.service.api.model.KitOptionItemsDTO;
import com.blckly.kit.service.api.respositories.KitRepository;
import com.blckly.kit.service.api.type.KitOptionCategory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

/**
 * Created by alexnikolayevsky on 5/25/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {KitServiceApiApplication.class})
@TestPropertySource(locations="classpath:application-test.properties")
@WebIntegrationTest
public class KitControllerTest {
    @Autowired
    private KitController controller;

    @Autowired
    protected KitRepository repository;

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testSaveKit() {
        KitDTO kitDTO = generateKit();
        Assert.isNull(kitDTO.getId());
        kitDTO = controller.saveKit(kitDTO);
        Assert.notNull(kitDTO);
        Assert.notNull(kitDTO.getId());
    }

    @Test
    public void testGetKit() {
        KitDTO kitDTO = controller.getKit(1);
        Assert.isNull(kitDTO);

        kitDTO = controller.saveKit(generateKit());
        kitDTO = controller.getKit(kitDTO.getId());
        Assert.notNull(kitDTO);
    }

    @Test
    public void testFindKit() {
        KitDTO[] kitsDTO = controller.findKits(null);
        Assert.isTrue(kitsDTO.length == 0);

        controller.saveKit(generateKit());
        kitsDTO = controller.findKits(null);
        Assert.notNull(kitsDTO);
        Assert.isTrue(kitsDTO.length == 1);

        kitsDTO = controller.findKits("AHDEE");
        Assert.notNull(kitsDTO);
        Assert.isTrue(kitsDTO.length == 1);

        kitsDTO = controller.findKits("NOTFOUND");
        Assert.notNull(kitsDTO);
        Assert.isTrue(kitsDTO.length == 0);
    }

    @Test
    public void testUpdateKit() {
        KitDTO kitDTO = generateKit();

        try {
            controller.updateKit(1, null);
            org.junit.Assert.fail("Should fail");
        } catch (Exception e) {
            Assert.isInstanceOf(NullPointerException.class, e);
        }

        // Test: update with null/different kit id vs the provided id
        try {
            controller.updateKit(1, kitDTO);
            org.junit.Assert.fail("fail");
        } catch (Exception e) {
            Assert.isInstanceOf(Exception.class, e);
        }

        // Test: update a nonexistent kit
        try {
            kitDTO.setId(1);
            controller.updateKit(1, kitDTO);
            org.junit.Assert.fail("Should fail");
        } catch (Exception e) {
            Assert.isInstanceOf(EntityNotFoundException.class, e);
        }

        // Test: update a kit
        kitDTO = controller.saveKit(generateKit());
        String originalDescription = kitDTO.getDescription();
        kitDTO.setDescription("Desc");
        try {
            kitDTO = controller.updateKit(kitDTO.getId(), kitDTO);
            Assert.isTrue(!kitDTO.getDescription().equals(originalDescription));
        } catch (Exception e) {
            fail();
        }

    }

    private KitDTO generateKit() {
        KitDTO kit = new KitDTO();
        kit.setName("AhDee");
        kit.setDescription("AhDee German Engineering");
        kit.setSku("AHDEE");
        kit.setKitOptions(generateKitOptions());
        return kit;
    }

    private List<KitOptionDTO> generateKitOptions() {
        KitOptionDTO kitOption = new KitOptionDTO();
        kitOption.setName("Wheels");
        kitOption.setDescription("Wheels");
        kitOption.setBaseFee(3.50);
        kitOption.setCategory(KitOptionCategory.WHEELS);
        kitOption.setStock(10);
        kitOption.setKitOptionItems(generateWheelItems());

        KitOptionDTO kitOption1 = new KitOptionDTO();
        kitOption1.setName("Engine Kit");
        kitOption1.setDescription("Engine Kit");
        kitOption1.setBaseFee(30.00);
        kitOption1.setCategory(KitOptionCategory.BATTERY);
        kitOption1.setStock(10);
        kitOption1.setKitOptionItems(generateBatteryKitItems());

        KitOptionDTO kitOption2 = new KitOptionDTO();
        kitOption2.setName("Body Kit");
        kitOption2.setDescription("Body type");
        kitOption2.setBaseFee(4.50);
        kitOption2.setCategory(KitOptionCategory.BODY);
        kitOption2.setStock(10);
        kitOption2.setKitOptionItems(generateBodyItems());

        KitOptionDTO kitOption3 = new KitOptionDTO();
        kitOption3.setName("Stickers");
        kitOption3.setDescription("Stickers");
        kitOption3.setBaseFee(4.50);
        kitOption3.setCategory(KitOptionCategory.STICKERS);
        kitOption3.setStock(10);
        kitOption3.setKitOptionItems(generateStickerItems());

        KitOptionDTO kitOption4 = new KitOptionDTO();
        kitOption4.setName("Sound System");
        kitOption4.setDescription("Sound System");
        kitOption4.setBaseFee(4.50);
        kitOption4.setCategory(KitOptionCategory.SOUND_SYSTEM);
        kitOption4.setStock(10);
        kitOption4.setKitOptionItems(generateSoundItems());

        List<KitOptionDTO> kitOptions = new ArrayList<KitOptionDTO>();
        kitOptions.add(kitOption);
        kitOptions.add(kitOption1);
        kitOptions.add(kitOption2);
        kitOptions.add(kitOption3);
        kitOptions.add(kitOption4);
        return kitOptions;
    }

    private List<KitOptionItemsDTO> generateWheelItems() {
        KitOptionItemsDTO kitOptionItem = new KitOptionItemsDTO();
        kitOptionItem.setName("4 Wheels");
        kitOptionItem.setDescription("4 AhDee Wheels");
        kitOptionItem.setBaseFee(2.50);
        kitOptionItem.setStock(10);

        List<KitOptionItemsDTO> kitOptionItems = new ArrayList<KitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        return kitOptionItems;
    }

    private List<KitOptionItemsDTO> generateBatteryKitItems() {
        KitOptionItemsDTO kitOptionItem = new KitOptionItemsDTO();
        kitOptionItem.setName("8Volt –Standard 4AA battery kit");
        kitOptionItem.setDescription("8Volt Batteries");
        kitOptionItem.setBaseFee(2.50);
        kitOptionItem.setStock(10);

        KitOptionItemsDTO kitOptionItem1 = new KitOptionItemsDTO();
        kitOptionItem1.setName("10volt – Standard 4AA battery kit");
        kitOptionItem1.setDescription("10Volt Batteries");
        kitOptionItem1.setBaseFee(3.50);
        kitOptionItem1.setStock(10);

        KitOptionItemsDTO kitOptionItem2 = new KitOptionItemsDTO();
        kitOptionItem2.setName("12volt – Premium Li battery kit");
        kitOptionItem2.setDescription("12volt Batteries");
        kitOptionItem2.setBaseFee(4.50);
        kitOptionItem2.setStock(10);

        List<KitOptionItemsDTO> kitOptionItems = new ArrayList<KitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        kitOptionItems.add(kitOptionItem1);
        kitOptionItems.add(kitOptionItem2);
        return kitOptionItems;
    }

    private List<KitOptionItemsDTO> generateBodyItems() {
        KitOptionItemsDTO kitOptionItem = new KitOptionItemsDTO();
        kitOptionItem.setName("FastBack");
        kitOptionItem.setDescription("FastBack Body");
        kitOptionItem.setBaseFee(2.50);
        kitOptionItem.setStock(10);

        KitOptionItemsDTO kitOptionItem1 = new KitOptionItemsDTO();
        kitOptionItem1.setName("Coupe");
        kitOptionItem1.setDescription("Coupe Body");
        kitOptionItem1.setBaseFee(3.50);
        kitOptionItem1.setStock(10);

        KitOptionItemsDTO kitOptionItem2 = new KitOptionItemsDTO();
        kitOptionItem2.setName("Saloon");
        kitOptionItem2.setDescription("Saloon Body");
        kitOptionItem2.setBaseFee(4.50);
        kitOptionItem2.setStock(10);

        List<KitOptionItemsDTO> kitOptionItems = new ArrayList<KitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        kitOptionItems.add(kitOptionItem1);
        kitOptionItems.add(kitOptionItem2);
        return kitOptionItems;
    }

    private List<KitOptionItemsDTO> generateStickerItems() {
        KitOptionItemsDTO kitOptionItem = new KitOptionItemsDTO();
        kitOptionItem.setName("Standard");
        kitOptionItem.setDescription("Standard Sticker");
        kitOptionItem.setBaseFee(2.50);
        kitOptionItem.setStock(10);

        KitOptionItemsDTO kitOptionItem1 = new KitOptionItemsDTO();
        kitOptionItem1.setName("R Motor Sport");
        kitOptionItem1.setDescription("R Motor Sport Sticker");
        kitOptionItem1.setBaseFee(3.50);
        kitOptionItem1.setStock(10);

        List<KitOptionItemsDTO> kitOptionItems = new ArrayList<KitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        kitOptionItems.add(kitOptionItem1);
        return kitOptionItems;
    }

    private List<KitOptionItemsDTO> generateSoundItems() {
        KitOptionItemsDTO kitOptionItem = new KitOptionItemsDTO();
        kitOptionItem.setName("Bows");
        kitOptionItem.setDescription("Bows Sound System");
        kitOptionItem.setBaseFee(2.50);
        kitOptionItem.setStock(10);

        KitOptionItemsDTO kitOptionItem1 = new KitOptionItemsDTO();
        kitOptionItem1.setName("Banger & Oaf");
        kitOptionItem1.setDescription("Banger & Oaf Sound System");
        kitOptionItem1.setBaseFee(3.50);
        kitOptionItem1.setStock(10);

        List<KitOptionItemsDTO> kitOptionItems = new ArrayList<KitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        kitOptionItems.add(kitOptionItem1);
        return kitOptionItems;
    }
}
