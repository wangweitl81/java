package com.david.general.anno;

import java.lang.reflect.Method;
import java.util.List;
import com.google.common.collect.Lists;

public class UseCaseTracker {

  public static void trackUseCases(List<Integer> caseList, Class<?> clz) {

    for (Method m : clz.getDeclaredMethods()) {
      UseCase uc = m.getAnnotation(UseCase.class);
      if (uc != null) {
        System.out.println("found use case : " + uc.id() + " " + uc.description());
        caseList.remove((Integer)uc.id());
      }
    }
    for (int i : caseList) {
      System.out.println("warning: missing use case : " + i);
    }
  }

  public static void main(String[] args) {
    List<Integer> caseList = Lists.newArrayList(47, 48, 49, 50);
    trackUseCases(caseList, PasswordUtils.class);
  }

}
