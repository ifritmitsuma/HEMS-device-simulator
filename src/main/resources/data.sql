INSERT INTO state (id, value, label, icon) VALUES (1, 'ON', 'On', 'on');
INSERT INTO state (id, value, label, icon) VALUES (2, 'OFF', 'Off', 'off');
INSERT INTO state (id, value, label, icon) VALUES (3, 'PLAYING', 'Playing', 'play');
INSERT INTO state (id, value, label, icon) VALUES (4, 'RECORDING', 'Recording', 'record');
INSERT INTO state (id, value, label, icon) VALUES (5, 'WASHING', 'Washing', 'play');

INSERT INTO action (id, code, name, state_after) VALUES (1, 'TURN_ON', 'On', 1);
INSERT INTO action (id, code, name, state_after) VALUES (2, 'TURN_OFF', 'Off', 2);
INSERT INTO action (id, code, name, state_after) VALUES (3, 'PLAY', 'Play', 3);
INSERT INTO action (id, code, name, state_after) VALUES (4, 'RECORD', 'Record', 4);
INSERT INTO action (id, code, name, state_after) VALUES (5, 'WASH', 'Wash', 5);

INSERT INTO power_source (id,code,io,name,power,type) VALUES
	 (1,'0001','INPUT','REN',2.50,'GRID'),
	 (2,'0002','INPUT','Roof Solar Panel 1',1.00,'SOLAR'),
	 (4,'0003','OUTPUT','Basement Battery',1.00,'BATTERY');

INSERT INTO product_type (id,`type`) VALUES
	 (1,'Appliance'),
	 (2,'HVAC'),
	 (3,'Light'),
	 (4,'Electronic');

INSERT INTO product_desc (id,description,icon,rating,type_id) VALUES
	 (1,'Washer','donut_small',1000,1),
	 (2,'Fridge','ad_units',1000,1),
	 (3,'Dryer','donut_small',1000,1),
	 (4,'Dishwasher','donut_small',1000,1),
	 (5,'Stove','center_focus_weak',1000,1),
	 (6,'Oven','credit_card',1000,1),
	 (7,'Microwave','microwave',800,1),
	 (8,'Coffee Machine','local_cafe',45,1),
	 (9,'Toaster','movie',200,1),
	 (10,'Boiler','whatshot',850,2);
INSERT INTO product_desc (id,description,icon,rating,type_id) VALUES
	 (11,'Fan','waves',20,2),
	 (12,'Heater','view_headline',500,2),
	 (13,'Lamp','lightbulb',10.00,3),
	 (14,'TV','tv',15,4),
	 (15,'TV Box','connected_tv',15,4),
	 (16,'Sound System','surround_sound',40,4),
	 (17,'Router','router',25,4),
	 (18,'Computer','computer',50,4),
	 (19,'Console',NULL,15,4);

INSERT INTO product (id,code,consumption,name,desc_id,state) VALUES
	 (3,'0001',100.00,'Fridge',2,1),
	 (2,'0002',0,'Washer',1,2),
	 (4,'0003',0,'Dryer',3,2),
	 (5,'0004',900.00,'Dishwasher',4,1),
	 (6,'0005',0,'Stove',5,2),
	 (7,'0006',0,'Oven',6,2),
	 (8,'0007',0,'Microwave',7,2),
	 (9,'0008',50.00,'Coffee Machine',8,1),
	 (10,'0009',0,'Toaster',9,2),
	 (11,'0010',850.00,'Boiler',10,1);
INSERT INTO product (id,code,consumption,name,desc_id,state) VALUES
	 (12,'0011',0,'Fan L',11,2),
	 (13,'0012',0,'Fan R',11,2),
	 (14,'0013',0,'Heater G',12,2),
	 (15,'0014',0,'Heater L',12,2),
	 (16,'0015',0,'Heater R',12,2),
	 (17,'0016',0.00,'Lamp G',13,2),
	 (18,'0017',0.00,'Lamp R',13,2),
	 (19,'0018',0,'Lamp L',13,2),
	 (20,'0019',0,'Lamp Outdoor',13,2),
	 (21,'0020',10.00,'TV',14,1);
INSERT INTO product (id,code,consumption,name,desc_id,state) VALUES
	 (22,'0021',10.00,'TV Box',15,1),
	 (23,'0022',0,'Sound System',16,2),
	 (24,'0023',30.00,'Wi-Fi Router',17,1),
	 (25,'0024',0,'My Laptop',18,2),
	 (26,'0030',0,'Playstation',19,2);