package com.example.JSFLab.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class YCordValidatorTest {

  private static final FacesContext fc = mock(FacesContext.class);
  private static final UIComponent mainComponent = mock(UIInput.class, RETURNS_DEEP_STUBS);
  private static final YCordValidator validator = new YCordValidator();
  private static final UIInput uiInput = mock(UIInput.class);

  @BeforeAll
  static void init() {
    when(mainComponent.getAttributes().get("pictureClick")).thenReturn(uiInput);
  }

  @Test
  void validateWithPictureExpectNoException() {
    when(uiInput.getValue()).thenReturn("true");
    validator.validate(fc, mainComponent, 5.0);
    validator.validate(fc, mainComponent, -5.5);
    validator.validate(fc, mainComponent, -1.0);
  }

  @Test
  void validateWithFormCorrectDataNoException() {
    when(uiInput.getValue()).thenReturn("false");
    validator.validate(fc, mainComponent, 3.0);
    validator.validate(fc, mainComponent, -1.0);
  }

  @Test
  void validateWithFormInvalidData() {
    when(uiInput.getValue()).thenReturn("false");
    assertThrows(ValidatorException.class, () -> validator.validate(fc, mainComponent, 5.0));
    assertThrows(ValidatorException.class, () -> validator.validate(fc, mainComponent, -5.2));
  }

}