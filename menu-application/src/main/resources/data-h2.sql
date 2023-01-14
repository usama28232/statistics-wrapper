-- menu
insert into menu (org, request, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'censusk17', 'Y', CURRENT_TIMESTAMP(), 'h2', CURRENT_TIMESTAMP(), 'h2');

-- menu_detail
insert into menu_detail (org, request, lang, text, title, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'censusk17', 'en','Karachi Census 2017', 'Karachi Census 2017', 'Y', CURRENT_TIMESTAMP(), 'h2', CURRENT_TIMESTAMP(), 'h2');

-- menu_stats_mapping
insert into menu_stats_mapping (org, request, s_org, s_view, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'censusk17', '0001', 'POP_K', 'Y', CURRENT_TIMESTAMP(), 'h2', CURRENT_TIMESTAMP(), 'h2');

insert into menu_stats_mapping (org, request, s_org, s_view, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'censusk17', '0001', 'AR_K', 'Y', CURRENT_TIMESTAMP(), 'h2', CURRENT_TIMESTAMP(), 'h2');

insert into menu_stats_mapping (org, request, s_org, s_view, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'censusk17', '0001', 'DEN_K', 'Y', CURRENT_TIMESTAMP(), 'h2', CURRENT_TIMESTAMP(), 'h2');

insert into menu_stats_mapping (org, request, s_org, s_view, active, add_dtm, add_by, chg_dtm, chg_by)
values ('0001', 'censusk17', '0001', 'POP_K_EW', 'Y', CURRENT_TIMESTAMP(), 'h2', CURRENT_TIMESTAMP(), 'h2');
