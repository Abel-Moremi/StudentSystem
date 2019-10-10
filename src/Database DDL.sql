/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Zozo
 * Created: Oct 10, 2019
 */

CREATE TABLE oak_Hotel (
    ht_no int NOT NULL,
	ht_name varchar(25),
	ht_city varchar(25),
	
	CONSTRAINTS PK_hotel PRIMARY KEY(ht_no)
);

CREATE TABLE oak_Guest (
    gt_no int NOT NULL,
	gt_name varchar(25),
	gt_address varchar(25),
	
	CONSTRAINTS PK_guest PRIMARY KEY(gt_no)
);

CREATE TABLE oak_Room (
    rm_no int NOT NULL UNIQUE, 
	htrm_no int NOT NULL,
	rm_type varchar(25),
	rm_price NUMERIC(6, 2),
	
	CONSTRAINTS PK_room PRIMARY KEY (rm_no, htrm_no),
	CONSTRAINTS FK_guest FOREIGN KEY (htrm_no) REFERENCES oak_Hotel(ht_no)
);

ALTER TABLE oak_Room
ADD CONSTRAINT CHK_Oak_room CHECK ((rm_type='Single' OR rm_type='Double' OR rm_type='Family') AND (rm_price>=100 AND rm_price<=1000) AND (rm_no>=1 AND rm_no<=100));

CREATE TABLE oak_Booking (
    htbk_no int NOT NULL,
	gtbk_no int NOT NULL,
	bk_dateFrom date NOT NULL,
	bk_dateto date,
	rmbk_no int,
	
	CONSTRAINTS PK_booking PRIMARY KEY (htbk_no, gtbk_no, bk_dateFrom),
	CONSTRAINTS FK_booking_htno FOREIGN KEY (htbk_no) REFERENCES oak_Hotel(ht_no),
	CONSTRAINTS FK_booking_gtno FOREIGN KEY (gtbk_no) REFERENCES oak_Guest(gt_no),
	CONSTRAINTS FK_booking_rmno FOREIGN KEY (rmbk_no) REFERENCES oak_Room(rm_no)
);

CREATE OR REPLACE TRIGGER trg_check_dates
  BEFORE INSERT OR UPDATE ON oak_Booking
  FOR EACH ROW
BEGIN
  IF( :new.bk_dateFrom < SYSDATE )
  THEN
    RAISE_APPLICATION_ERROR( -20001, 
          'Invalid DA=ateFrom: CloseDate must be greater than the current date - value = ' || 
          to_date( :new.bk_dateFrom, 'yyyy-mm-dd' ) );
  END IF;
  IF( :new.bk_dateto <= SYSDATE )
  THEN
    RAISE_APPLICATION_ERROR( -20001, 
          'Invalid DA=ateFrom: CloseDate must be greater than the current date - value = ' || 
          to_date( :new.bk_dateto, 'yyyy-mm-dd' ) );
  END IF;
END;

-- Data Entry 
-- ***** Hotel Table *****

INSERT INTO oak_Hotel
VALUES (1234, 'Oak Tree', 'Gaborone');

-- ***** Hotel Guest *****

INSERT INTO oak_Guest
VALUES (1001, 'Shellock Holmes', '22 Bakers Street');

INSERT INTO oak_Guest
VALUES (1002, 'Donald Trump', 'Twitter Streets');

INSERT INTO oak_Guest
VALUES (1003, 'Iceberg Slim', 'These Streets');

INSERT INTO oak_Guest
VALUES (1004, 'Denise The Menace', '301 Childhood lane');

-- ****** Hotel Room ******

INSERT INTO oak_Room
VALUES (1, 1234, 'Single', 100.00);

INSERT INTO oak_Room
VALUES (2, 1234, 'Double', 200.00);

INSERT INTO oak_Room
VALUES (3, 1234, 'Family', 300.00);

-- ***** Hotel Booking ******

INSERT INTO oak_Booking
VALUES (1234, 1001, '2019-10-10', '2019-10-12', 1);

INSERT INTO oak_Booking
VALUES (1234, 1002, '2019-10-10', '2019-10-12', 2);


-- Extra Sauce i need to cover
ALTER SESSION SET NLS_DATE_FORMAT = 'yyyy-mm-dd';

