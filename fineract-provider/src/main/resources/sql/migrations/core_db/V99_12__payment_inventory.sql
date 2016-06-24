CREATE TABLE `m_payment_inventory` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `loan_id` mediumtext,
  `periods` mediumint(9) DEFAULT NULL,
  `is_directDebitActive` tinyint(1) DEFAULT NULL,
  `pdc_type` int(2) DEFAULT NULL,
  `is_SeriesCheques` tinyint(1) DEFAULT NULL,
  `is_chequesDispatched` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `m_payment_inventory_pdc` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `payment_id` int(11) DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  `amount` decimal(19,6) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `cheque_date` date DEFAULT NULL,
  `cheque_no` int(11) DEFAULT NULL,
  `name_of_bank` varchar(11) DEFAULT NULL,
  `ifsc_code` varchar(11) DEFAULT NULL,
  `present_type_of` int(11) DEFAULT NULL,
  `make_presentation` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

INSERT INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`)
VALUES
	( 'portfolio', 'CREATE_PAYMENTINVENTORY', 'PAYMENTINVENTORY', 'CREATE', 0),
	( 'portfolio', 'READ_PAYMENTINVENTORY', 'PAYMENTINVENTORY', 'READ', 0),
	( 'portfolio', 'UPDATE_PAYMENTINVENTORY', 'PAYMENTINVENTORY', 'UPDATE', 0);
	
	
	
