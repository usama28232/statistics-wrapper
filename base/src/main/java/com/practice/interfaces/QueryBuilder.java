package com.practice.interfaces;

public interface QueryBuilder {

    static final int ST_INDEX = 0;
    static final String SPACE = " ";
    static final String KEYWORD_FROM = "FROM";
    static final String KEYWORD_WHERE = "WHERE";
    static final String SQ_BRACKET_O = "[";
    static final String $$ = "$$";

    public String build();

}
