-- stats_builder
insert into stats_builder (s_org, s_view, aggregate_field, aggregate_func, custom_agg, filter, max_rows, selection,
                           user_id, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'POP_K', 'population', 'SM', null,
        'where c_year = ''2017'' and country=''Pakistan'' and city=''Karachi''', 1,
        'census', 'dev', 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');
insert into stats_builder (s_org, s_view, aggregate_field, aggregate_func, custom_agg, filter, max_rows, selection,
                           user_id, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'AR_K', 'area', 'SM', null,
        'where c_year = ''2017'' and country=''Pakistan'' and city=''Karachi'' ', 1,
        'census', 'dev', 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');
insert into stats_builder (s_org, s_view, aggregate_field, aggregate_func, custom_agg, filter, max_rows, selection,
                           user_id, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'DEN_K', 'density', 'SM', null,
        'where c_year = ''2017'' and country=''Pakistan'' and city=''Karachi'' ', 1,
        'census', 'dev', 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');
insert into stats_builder (s_org, s_view, aggregate_field, aggregate_func, custom_agg, filter, max_rows, selection,
                           user_id, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'POP_K_EW', 'population', 'SM', null,
        'where c_year = ''2017'' and country=''Pakistan'' and city=''Karachi'' and district in (''East'',''West'')', 1,
        'census', 'dev', 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');
insert into stats_builder (s_org, s_view, aggregate_field, aggregate_func, custom_agg, filter, max_rows, selection,
                           user_id, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'POP_K_KM', 'population', 'SM', null,
        'where c_year = ''2017'' and country=''Pakistan'' and city=''Karachi'' and district in (''Korangi'',''Malir'')',
        1,
        'census', 'dev', 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');

-- census
insert into census (c_year, country, state, city, district, population, density, area, active, add_dtm, add_by,
                        chg_dtm,
                        chg_by)
values ('2017', 'Pakistan', 'Sindh', 'Karachi', 'Central', 2971382, 43063, 69, 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');
insert into census (c_year, country, state, city, district, population, density, area, active, add_dtm, add_by,
                        chg_dtm,
                        chg_by)
values ('2017', 'Pakistan', 'Sindh', 'Karachi', 'Korangi', 2577556, 23866, 108, 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');
insert into census (c_year, country, state, city, district, population, density, area, active, add_dtm, add_by,
                        chg_dtm,
                        chg_by)
values ('2017', 'Pakistan', 'Sindh', 'Karachi', 'East', 2875315, 20685, 139, 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(),
        'h2');
insert into census (c_year, country, state, city, district, population, density, area, active, add_dtm, add_by,
                        chg_dtm,
                        chg_by)
values ('2017', 'Pakistan', 'Sindh', 'Karachi', 'South', 1769230, 14501, 122, 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');
insert into census (c_year, country, state, city, district, population, density, area, active, add_dtm, add_by,
                        chg_dtm,
                        chg_by)
values ('2017', 'Pakistan', 'Sindh', 'Karachi', 'West', 3907065, 4205, 929, 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');
insert into census (c_year, country, state, city, district, population, density, area, active, add_dtm, add_by,
                        chg_dtm,
                        chg_by)
values ('2017', 'Pakistan', 'Sindh', 'Karachi', 'Malir', 1924364, 890, 2160, 'Y', CURRENT_TIMESTAMP(), 'h2',
        CURRENT_TIMESTAMP(), 'h2');

