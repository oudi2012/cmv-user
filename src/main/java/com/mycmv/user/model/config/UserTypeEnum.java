package com.mycmv.user.model.config;

/***
 * @author a
 */
public enum UserTypeEnum {
    /** admin */
    ADMIN("admin", "admin"),
    TEACHER("teacher", "teacher"),
    GUARDIAN("guardian", "guardian"),
    STUDENT("student", "student");

    private final String code;
    private final String title;

    UserTypeEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
