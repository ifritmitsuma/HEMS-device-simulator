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