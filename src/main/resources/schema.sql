INSERT INTO comunidad(nombre, direccion, cif, cp ,municipio, provincia, presidente_id, iban, administrador)
VALUES ('Carrer Bosquet 23', 'Carrer Bosquet, 23', 'B71231823', '08100','Mollet del Vallès','Barcelona',9,'ES26 5434 6552 4123 1236 7655', 'ilopez')
,('Avinguda Mar 112', 'Avinguda Mar 112', 'B16732345', '08100','Mollet del Vallès','Barcelona',1,'ES34 3422 5811 0031 8728 1852', 'ilopez');

INSERT INTO presupuesto(fecha_inicial, fecha_final, nombre, saldo_inicial, comunidad_id)
VALUES ('2023/01/01','2023/12/31','Presupuesto ordinario','4375.23',1),
       ('2023/01/01','2023/12/31','Presupuesto ordinario','1225.80',2);

INSERT INTO partida(nombre, autorizacion, presupuesto_id)
VALUES ('Electricidad escalera',false, 1),
       ('Conservación material contraincendios', false, 1),
       ('Seguro comunidad', false, 1),
       ('Mantenimiento ascensor', false, 1),
       ('Suministro de agua', false, 1),
       ('Imprevistos', true, 1),
       ('Electricidad escalera',false, 2),
       ('Conservación material contraincendios', false, 2),
       ('Seguro comunidad', false, 2),
       ('Mantenimiento ascensor', false, 2),
       ('Suministro de agua', false, 2),
       ('Imprevistos', true, 2);

INSERT INTO entidad(nombre, coeficiente, comunidad_id)
VALUES ('Local', 32.05, 1),
       ('1-1',9.65,1),
       ('1-2',9.4,1),
       ('2-1',9.65,1),
       ('2-2',9.4,1),
       ('3-1',9.65,1),
       ('3-2',9.4,1),
       ('Ático',10.8,1),
       ('Local', 33.34, 2),
       ('1-1',33.33,2),
       ('1-2',33.33,2);


INSERT INTO entidad_propietario(entidad_id, cliente_id, porcentaje_propiedad)
VALUES (1,2,100),
       (2,3,100),
       (3,1,100),
       (4,4,50),
       (4,5,50),
       (5,6,50),
       (5,7,50),
       (6,8,50),
       (6,9,50),
       (7,10,100),
       (8,11,100),
       (9,1,100),
       (10,2,100),
       (11,3,100);

INSERT INTO publicacion(fecha_inicio, fecha_fin, titulo, mensaje, fecha_evento, comunidad_id)
VALUES ('2023/04/28', '2023/05/15', 'Asamblea ordinaria','Mediante la presente se convoca a los propietarios a reunión ordinaria a celebrar en el vestíbulo de la
comunidad el próximo <b>15 de abril a las 19:00 horas</b>. El orden del día es el siguiente:
Presentación estado de cuentas ejercicio 2022.
Relación de cuotas pendientes a día de la reunión.
Renovación cargos presidente y secretario-administrador.
Ruego y preguntas.', '2023/08/15', 1),
       ('2023/04/28', '2023/08/25', 'Reparación iterfonos','Se ruega a los vecinos a los que no les funcione bien el interfono, que envíen un mensaje al administrador. ', '2023/06/25', 1),
('2023/03/23', '2023/03/25', 'Nota antigua','Mensaje antiguo. ', '2023/03/22', 1);


INSERT INTO recibo(concepto, fecha_recibo, importe, fecha_pago, pagado, entidad_id, comunidad_id)
VALUES ('Cuota ordinaria anual', '2023/01/01', 250, '2023/01/01', true, 1, 1),
       ('Cuota ordinaria mensual', '2023/01/01', 45, '2023/01/01', true, 2, 1),
       ('Cuota ordinaria mensual', '2023/02/01', 45, '2023/02/01', true, 2, 1),
       ('Cuota ordinaria mensual', '2023/03/01', 45, '2023/03/01', true, 2, 1),
       ('Cuota ordinaria mensual', '2023/04/01', 45, '2023/04/01', true, 2, 1),
       ('Cuota ordinaria mensual', '2023/05/01', 45, '2023/05/01', true, 2, 1),
       ('Cuota ordinaria mensual', '2023/01/01', 45, '2023/01/01', true, 3, 1),
       ('Cuota ordinaria mensual', '2023/02/01', 45, '2023/02/01', true, 3, 1),
       ('Cuota ordinaria mensual', '2023/03/01', 45, '2023/03/01', true, 3, 1),
       ('Cuota ordinaria mensual', '2023/04/01', 45, null, false, 3, 1),
       ('Cuota ordinaria mensual', '2023/05/01', 45, null, false, 3, 1),
       ('Cuota ordinaria mensual', '2023/05/01', 45, null, false, 6, 1),
       ('Cuota ordinaria mensual', '2023/01/01', 45, '2023/01/01', true, 4, 1),
       ('Cuota ordinaria mensual', '2023/02/01', 45, '2023/02/01', true, 4, 1),
       ('Cuota ordinaria mensual', '2023/03/01', 45, '2023/03/01', true, 4, 1),
       ('Cuota ordinaria mensual', '2023/04/01', 45, '2023/04/01', true, 4, 1),
       ('Cuota ordinaria mensual', '2023/05/01', 45, '2023/05/01', true, 4, 1),
       ('Cuota ordinaria mensual', '2023/01/01', 45, '2023/01/01', true, 5, 1),
       ('Cuota ordinaria mensual', '2023/02/01', 45, '2023/02/01', true, 5, 1),
       ('Cuota ordinaria mensual', '2023/03/01', 45, '2023/03/01', true, 5, 1),
       ('Cuota ordinaria mensual', '2023/04/01', 45, '2023/04/01', true, 5, 1),
       ('Cuota ordinaria mensual', '2023/05/01', 45, '2023/05/01', true, 5, 1),
       ('Cuota ordinaria mensual', '2023/01/01', 45, '2023/01/01', true, 6, 1),
       ('Cuota ordinaria mensual', '2023/02/01', 45, '2023/02/01', true, 6, 1),
       ('Cuota ordinaria mensual', '2023/03/01', 45, '2023/03/01', true, 6, 1),
       ('Cuota ordinaria mensual', '2023/04/01', 45, '2023/04/01', true, 6, 1),
       ('Cuota ordinaria mensual', '2023/05/01', 45, '2023/05/01', true, 6, 1),
       ('Cuota ordinaria mensual', '2023/01/01', 45, '2023/01/01', true, 7, 1),
       ('Cuota ordinaria mensual', '2023/02/01', 45, '2023/02/01', true, 7, 1),
       ('Cuota ordinaria mensual', '2023/03/01', 45, '2023/03/01', true, 7, 1),
       ('Cuota ordinaria mensual', '2023/04/01', 45, '2023/04/01', true, 7, 1),
       ('Cuota ordinaria mensual', '2023/05/01', 45, '2023/05/01', true, 7, 1),
       ('Cuota ordinaria mensual', '2023/01/01', 45, '2023/01/01', true, 8, 1),
       ('Cuota ordinaria mensual', '2023/02/01', 45, '2023/02/01', true, 8, 1),
       ('Cuota ordinaria mensual', '2023/03/01', 45, '2023/03/01', true, 8, 1),
       ('Cuota ordinaria mensual', '2023/04/01', 45, '2023/04/01', true, 8, 1),
       ('Cuota ordinaria mensual', '2023/05/01', 45, '2023/05/01', true, 8, 1);

INSERT INTO factura (comunidad_id, partida_id, proveedor_id, fecha_factura, numero_factura, descripcion, importe, pagada, autorizada, fecha_pago)
VALUES (1, 1, 12, '2023/01/05', null, 'Electricidad enero', 156.34, true, true, '2023/01/05'),
       (1, 1, 12, '2023/02/06', null, 'Electricidad febrero', 153.51, true, true, '2023/02/06'),
       (1, 1, 12, '2023/03/05', null, 'Electricidad marzo', 147.88, true, true, '2023/03/05'),
       (1, 1, 12, '2023/04/05', null, 'Electricidad abril', 163.11, true, true, '2023/04/05'),
       (1, 1, 12, '2023/05/07', null, 'Electricidad mayo', 157.25, true, true, '2023/05/07'),
       (1, 2, 13, '2023/03/25', 'M23-662', 'Conservación extintores', 57.30, true, true, '2023/03/25'),
       (1, 3, 14, '2023/04/28', null, 'Seguro anual 2023-2024', 792.86, true, true, '2023/04/28'),
       (1, 4, 15, '2023/01/01', null, '1r trimestre', 323.11, true, true, '2023/01/01'),
       (1, 4, 15, '2023/04/01', null, '2º trimestre', 323.11, true, true, '2023/04/01'),
       (1, 5, 16, '2023/02/02', null, '1r trimestre', 25.55, true, true, '2023/02/02'),
       (1, 5, 16, '2023/05/03', null, '2º trimestre', 25.49, true, true, '2023/05/03'),
       (1, 6, 17, '2023/05/03', '2023-23', 'Reparación bombín', 25.49, false, false, null),
       (1, 6, 18, '2023/05/03', '2350', 'Buzones', 123.54, false, false, null);


