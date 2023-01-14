package com.practice.web.engine;

import com.practice.enums.AggregateFunctions;
import com.practice.interfaces.QueryBuilder;
import com.practice.web.entities.StatsBuilder;
import org.apache.logging.log4j.util.Strings;

public class QueryExtractor implements QueryBuilder {

    private StatsBuilder entity;

    public QueryExtractor(StatsBuilder stats) {
        this.entity = stats;
    }

    public String build() {
        String wh = this.getWhereClause();
        wh = this.getWhereUserGroup(wh);
        return this.getSelection(wh).concat(wh);
    }

    private String getAggregateFunctions(String aField) {
        String reply = null;
        if (Strings.isEmpty(this.entity.getCustom_agg())) {
            if (Strings.isEmpty(aField)) {
                reply = "select count(*) ";
            } else {
                AggregateFunctions functions = AggregateFunctions.valueOf(this.entity.getAggregate_func());
                switch (functions) {
                    case SM:
                        reply = "select sum(".concat(aField).concat(") ");
                        break;
                    case MN:
                        reply = "select min(".concat(aField).concat(") ");
                        break;
                    case MX:
                        reply = "select max(".concat(aField).concat(") ");
                        break;
                    case AV:
                        reply = "select avg(".concat(aField).concat(") ");
                        break;
                    default:
                        reply = "select count(".concat(aField).concat(") ");
                        break;
                }
            }
        } else {
            int bracketIndex = this.entity.getCustom_agg().indexOf(this.SQ_BRACKET_O);
            if (bracketIndex < this.ST_INDEX) {
                int $$index = this.entity.getCustom_agg().indexOf(this.$$);
            }
            if (bracketIndex >= this.ST_INDEX) {
                String temp = this.entity.getCustom_agg();
                temp = this.replaceParams(temp);
                reply = "select ".concat(temp).concat(" ");
            } else {
                reply = "select ".concat(this.entity.getCustom_agg()).concat(" ");
            }
        }
        return reply;
    }

    private String getAggregateFunctions2(String aField, String entity) {
        String reply = null;
        if (Strings.isEmpty(this.entity.getCustom_agg())) {
            if (Strings.isEmpty(aField)) {
                reply = "select count(*) from ".concat(entity).concat(" ");
//                reply = "select count(*) from ".concat(entity).concat(" %%whereClause");
            } else {
                AggregateFunctions functions = AggregateFunctions.valueOf(this.entity.getAggregate_func());
                switch (functions) {
                    case SM:
                        reply = "select sum(".concat(aField).concat(") from ").concat(entity);
                        break;
                    case MN:
                        reply = "select min(".concat(aField).concat(") from ").concat(entity);
                        break;
                    case MX:
                        reply = "select max(".concat(aField).concat(") from ").concat(entity);
                        break;
                    case AV:
                        reply = "select avg(".concat(aField).concat(") from ").concat(entity);
                        break;
                    default:
                        reply = "select count(".concat(aField).concat(") from ").concat(entity);
                        break;
                }
            }
        } else {
            int bracketIndex = this.entity.getCustom_agg().indexOf(this.SQ_BRACKET_O);
            if (bracketIndex < this.ST_INDEX) {
                int $$index = this.entity.getCustom_agg().indexOf(this.$$);
            }
            if (bracketIndex >= this.ST_INDEX) {
                String temp = this.entity.getCustom_agg();
                temp = this.replaceParams(temp);
                reply = "select ".concat(temp).concat(" from ").concat(entity); //.concat(" %%whereClause");
            } else {
                reply = "select ".concat(this.entity.getCustom_agg()).concat(" from ")
                        .concat(entity); //.concat(" %%whereClause");
            }
        }
        return reply;
    }

    private String getSelection(String whereClause) {
        String reply;
        String aField = this.entity.getAggregateField();
        String temp = whereClause.toUpperCase();
        int fromIndex = temp.indexOf(this.KEYWORD_FROM);
        if (fromIndex >= ST_INDEX) {
            reply = this.getAggregateFunctions(aField);
        } else {
            String entName = this.entity.getSelection().concat(" ");
            String _ent = this.getEntityName();
            reply = this.getAggregateFunctions2(aField, entName);
        }
        return reply;
    }

    private String getMoreWhere() {
        return "";
    }

    private String getWhereClause() {
        String processedView = this.processView();
        String moreWhere = this.getMoreWhere(); /*entity.getFilter().indexOf(KEYWORD_WHERE) >= ST_INDEX ?
                entity.getFilter().substring(
                        entity.getFilter().indexOf(KEYWORD_WHERE) + this.KEYWORD_WHERE.length()
                ) : ""; // ???*/
        String whereClause = this.entity.getFilter(); // ??

        if (Strings.isNotEmpty(moreWhere)) {
            if (Strings.isEmpty(whereClause)) {
                whereClause = moreWhere;
            } else {
                String tempString = whereClause.toUpperCase();
                int fromIndex = tempString.indexOf(this.KEYWORD_FROM);
                if (fromIndex >= ST_INDEX) {
                    int whereIndex = tempString.indexOf(this.KEYWORD_WHERE);
                    if (whereIndex >= 0) {
                        whereClause = whereClause.concat(" and (%%moreWhere%%%)");
                    } else {
                        whereClause = processedView.concat(" where (%%moreWhere%%%)");
                    }
                } else {
                    whereClause = processedView.concat(this.SPACE)
                            .concat(whereClause).concat(" and (%%moreWhere%%%)");
                }
            }
        }
        return whereClause;
    }

    private String getEntityName() {
        return "";
    }

    private String getWhereUserGroup(String whereClause) {
        return whereClause;
    }

    private String processView() {
        String reply;
        int fromIndex = this.entity.getFilter().toUpperCase().indexOf(this.KEYWORD_FROM);
        if (fromIndex >= ST_INDEX) {
            int whereIndex = this.entity.getFilter().toUpperCase().indexOf(this.KEYWORD_WHERE.concat(this.SPACE));
            if (whereIndex >= ST_INDEX) {
                reply = this.entity.getFilter().substring(fromIndex + KEYWORD_WHERE.length(), whereIndex - 1);
            } else {
                reply = this.entity.getFilter().substring(fromIndex + KEYWORD_WHERE.length());
            }
        } else {
            reply = this.entity.getSelection().concat(" ");
        }
        return reply;
    }

    private String replaceParams(String value) {
        return value;
    }

}
