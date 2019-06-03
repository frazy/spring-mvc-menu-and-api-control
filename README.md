# 简单权限模型

这里是一个样例，可以快速的定义角色、权限，以及进行访问控制。

适用与角色、权限变动相对较少的系统。

本系统样例基于SpringBoot。

步骤如下：

## 1.定义ZoneEnum

  Zone是指系统中API需要划分的区域。

  可以是一个模块一个Zone。
  ```java
  public enum ZoneEnum implements EnumValue {
      // 特殊虚拟Zone
      OPEN("OPEN"), // 不需要登录对外公开的API
      LOGIN("LOGIN"), // 只要登录即可访问的API

      // 具体Zone
      CAT("猫猫管理"), //
      DOG("狗狗管理"), //
      CODER("码农管理"), //
      ;
  ```

## 2.标记Zone

  在系统的Controller类或者Controller的方法上增加annotation注解
  ```java
  @ApiZone(ZoneEnum.CAT)
  ```
  同一Controller类方法上的注解优先级高于class上的。
  ```java
  @ApiZone(ZoneEnum.CAT)
  @RestController
  public class CatController {

      @GetMapping("/cats")
      public void getCats() {
      }

      @ApiZone({ ZoneEnum.CAT, ZoneEnum.CODER })
      @PostMapping("/say/miao/to/dog")
      public void say() {
      }

  }
  ```

## 3.定义RoleEnum

  规划你系统中的角色枚举，与Zone建立关联。
  ```java
  public enum RoleEnum implements EnumValue {
      CAT_ROLE("猫猫管理员", new ZoneEnum[] { ZoneEnum.CAT }), //
      DOG_ROLE("狗狗管理员", new ZoneEnum[] { ZoneEnum.DOG }), //
      MASTER("猫猫狗狗管理员", new ZoneEnum[] { ZoneEnum.CAT, ZoneEnum.DOG }), //
      GOD("GOD", new ZoneEnum[] {}), // 超级管理员
      ;
  ```

## 4.ApiZoneAspect生效

  ApiZoneAspect将会获取当前访问Controller的@ApiZone，然后做不同的控制逻辑。

## 注意：

  - 用户/账号可以是DB中的持久化对象，然后与上面定义的Role建立关联。

