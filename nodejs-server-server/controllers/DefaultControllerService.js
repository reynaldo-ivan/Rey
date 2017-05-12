'use strict';

exports.menuPOST = function(args, res, next) {
  /**
   * /menu
   * menu bancanet
   *
   * menu Menu Consulta menu por usuario (optional)
   * returns inline_response_200
   **/
  var examples = {};
  examples['application/json'] = {
	"id": 2,
	"Menu": [{
		"id": "01",
		"name": "CONSULTAS",
		"componet": "",
		"submenu": [{
			"id": "0101",
			"componet": "saldos"
		}, {
			"id": "0102",
			"name": "Estados de cuenta",
			"componet": "",
			"submenu": [{
				"id": "010201",
				"name": "En linea",
				"componet": "Estados de Cuenta en linea"
			}, {
				"id": "010202",
				"name": "Por solicitud (cheques)",
				"componet": "Estados de Cuenta por solicitud para cuentas de cheques"
			}, {
				"id": "010203",
				"name": "Histórico (PDF)",
				"componet": "Histórico (PDF)"
			}]
		}, {
			"id": "0103",
			"componet": "Movimientos (cheques)"
		}, {
			"id": "0104",
			"componet": "Tarjeta Corporativa"
		}, {
			"id": "0105",
			"componet": "Historial"
		}]
	}, {
		"id": "02",
		"name": "TRANSFERENCIAS Y PAGOS",
		"componet": "",
		"submenu": [{
			"id": "0201",
			"componet": "Multipagos"
		}, {
			"id": "0202",
			"componet": "Resumen"
		}, {
			"id": "0203",
			"name": "Transferencias",
			"componet": "",
			"submenu": [{
				"id": "020301",
				"name": "Cuentas propias Banamex",
				"componet": "Transferencia entre cuentas propias Banamex"
			}, {
				"id": "020302",
				"name": "Otras cuentas",
				"componet": "Estados de Cuenta por solicitud para cuentas de cheques"
			}]
		}, {
			"id": "0204",
			"name": "Pagos",
			"componet": "",
			"submenu": [{
				"id": "020401",
				"name": "Targetas de credito",
				"componet": "Pago de tarjetas de crédito"
			}, {
				"id": "020402",
				"name": "Servicios",
				"componet": "Pago de Servicios"
			}, {
				"id": "020403",
				"name": "Domiciliados",
				"componet": "Pagos domiciliados"
			}]
		}, {
			"id": "0205",
			"name": "Nómina Banamex",
			"componet": "",
			"submenu": [{
				"id": "020501",
				"name": "Crear archivo",
				"componet": "Crear archivo"
			}, {
				"id": "020502",
				"name": "Cargar archivo",
				"componet": "Cargar archivo"
			}, {
				"id": "020503",
				"name": "Consultar archivo",
				"componet": "Consultar archivo"
			}, {
				"id": "020504",
				"name": "Administración de tarjetas",
				"componet": "Administración de tarjetas"
			}, {
				"id": "020505",
				"name": "Reportes y contratos",
				"componet": "Reportes y contratos"
			}]
		}, {
			"id": "0206",
			"name": "Transferencias Masivas",
			"componet": "",
			"submenu": [{
				"id": "020601",
				"name": "Crear archivo",
				"componet": "Crear archivo"
			}, {
				"id": "020602",
				"name": "Cargar archivo",
				"componet": "Cargar archivo"
			}, {
				"id": "020603",
				"name": "Consultar archivo",
				"componet": "Consultar archivo"
			}]
		}, {
			"id": "0207",
			"name": "Depositos Masivos",
			"componet": "Depositos Masivos"
		}]
	}, {
		"id": "03",
		"name": "IMPUESTOS Y CONTRIBUCIONES",
		"componet": "",
		"submenu": [{
			"id": "0301",
			"componet": "Resumen"
		}, {
			"id": "0302",
			"componet": "Impuestos Federales"
		}, {
			"id": "0303",
			"componet": "Impuestos Estatales"
		}, {
			"id": "0304",
			"componet": "Pago a GDF"
		}, {
			"id": "0305",
			"componet": "Pago TESOFE"
		}, {
			"id": "0306",
			"componet": "SUA"
		}, {
			"id": "0307",
			"componet": "ISSSTE"
		}, {
			"id": "0308",
			"componet": "Comprobantes"
		}]
	}, {
		"id": "04",
		"name": "COBRANZA",
		"componet": "",
		"submenu": [{
			"id": "0401",
			"componet": "Informe de Pagos"
		}, {
			"id": "0402",
			"name": "Domiciliación",
			"componet": "",
			"submenu": [{
				"id": "040201",
				"name": "Crear archivo",
				"componet": "Crear archivo"
			}, {
				"id": "040202",
				"name": "Cargar archivo",
				"componet": "Cargar archivo"
			}, {
				"id": "040203",
				"name": "Consultar archivo",
				"componet": "Consultar archivo"
			}]
		}, {
			"id": "0403",
			"name": "Cobranza Universal",
			"componet": "",
			"submenu": [{
				"id": "040301",
				"name": "Consulta",
				"componet": "Consulta"
			}, {
				"id": "040302",
				"name": "Alta de Cuentas Virtuales",
				"componet": "Alta de Cuentas Virtuales"
			}, {
				"id": "040302",
				"name": "Alias en Cuentas Virtuales",
				"componet": "Alias en Cuentas Virtuales"
			}]
		}, {
			"id": "0404",
			"name": "Cobros Masivos",
			"componet": "",
			"submenu": [{
				"id": "040401",
				"name": "Crear archivo",
				"componet": "Crear archivo"
			}, {
				"id": "040402",
				"name": "Cargar archivo",
				"componet": "Cargar archivo"
			}, {
				"id": "040403",
				"name": "Consultar archivo",
				"componet": "Consultar archivo"
			}]
		}]
	}, {
		"id": "05",
		"name": "INVERSIONES",
		"componet": "",
		"submenu": [{
			"id": "0501",
			"componet": "Resumen"
		}, {
			"id": "0502",
			"componet": "A la Vista"
		}, {
			"id": "0503",
			"componet": "Pagaré"
		}, {
			"id": "0504",
			"componet": "Fondos Banamex"
		},  {
			"id": "0505",
			"componet": "Fondos Horizonte"
		}, {
			"id": "0506",
			"componet": "Comprobantes"
		}]
	}, {
		"id": "06",
		"name": "SERVICIOS ADICIONALES",
		"componet": "",
		"submenu": [{
			"id": "0601",
			"name": "Operaciones de Crédito",
			"componet": "",
			"submenu": [{
				"id": "060101",
				"name": "Consultas",
				"componet": "Consultas"
			}, {
				"id": "060102",
				"name": "Disposición",
				"componet": "Disposición"
			}, {
				"id": "060103",
				"name": "Renovaciones",
				"componet": "Renovaciones"
			}, {
				"id": "060104",
				"name": "Pagos",
				"componet": "Pagos"
			}]
		}, {
			"id": "0602",
			"name": "Pemex",
			"componet": "",
			"submenu": [{
				"id": "060201",
				"name": "Facturación",
				"componet": "Facturación"
			}, {
				"id": "060202",
				"name": "Consultar",
				"componet": "Consulta"
			}, {
				"id": "060203",
				"name": "Estado de Cuenta",
				"componet": "Estado de Cuenta"
			}, {
				"id": "060204",
				"name": "Refinación",
				"componet": "Refinación"
			}]
		}, {
			"id": "0603",
			"name": "Financiamiento a Proveedores",
			"componet": "",
			"submenu": [{
				"id": "060301",
				"name": "Proveedores",
				"componet": "Proveedores"
			}, {
				"id": "060302",
				"name": "Compradores",
				"componet": "Compradores"
			}]
		}, {
			"id": "0604",
			"name": "Cuenta Balance Cero Estructuras",
			"componet": "",
			"submenu": [{
				"id": "060401",
				"name": "Estructuras - Nueva",
				"componet": "Estructuras - Nueva"
			}, {
				"id": "060402",
				"name": "Estructuras - Baja",
				"componet": "Estructuras - Baja"
			}, {
				"id": "060403",
				"name": "Estructuras - Modificar",
				"componet": "Estructuras - Modificar"
			}, {
				"id": "060404",
				"name": "Estructuras - Consultar",
				"componet": "Estructuras - Consultar"
			}]
		}]
	}, {
		"id": "07",
		"name": "ADMINISTRACIÓN",
		"componet": "",
		"submenu": [{
			"id": "0701",
			"name": "Usuarios",
			"componet": "",
			"submenu": [{
				"id": "070101",
				"name": "Alta de Usuario",
				"componet": "Alta de Usuario"
			}, {
				"id": "070102",
				"name": "Administración de usuarios",
				"componet": "Administración de usuarios"
			}, {
				"id": "070103",
				"name": "NetKey multiclientes",
				"componet": "NetKey multiclientes"
			}]
		}, {
			"id": "0702",
			"componet": "Cuentas Propias"
		}, {
			"id": "0703",
			"name": "Otras Cuentas",
			"componet": "",
			"submenu": [{
				"id": "070301",
				"name": "Altas de cuentas",
				"componet": "Altas de cuentas"
			}, {
				"id": "070302",
				"name": "Administración de cuentas",
				"componet": "Administración de cuentas"
			}, {
				"id": "070303",
				"name": "Descarga por solicitud",
				"componet": "Descarga por solicitud"
			}, {
				"id": "070304",
				"name": "Consultar archivo",
				"componet": "Consultar archivo"
			}]
		}, {
			"id": "0704",
			"componet": "Pendientes de Autorización"
		},{
			"id": "0705",
			"componet": "Chequeras"
		}, {
			"id": "0706",
			"name": "Configuración",
			"componet": "",
			"submenu": [{
				"id": "070601",
				"name": "Vista resumen de saldos",
				"componet": "Vista resumen de saldos"
			},{
				"id": "070602",
				"name": "Validación cuentas de archivos",
				"componet": "Validación cuentas de archivos"
			},{
				"id": "070603",
				"name": "Carga / Descarga de archivos",
				"componet": "Carga / Descarga de archivos"
			},{
				"id": "070604",
				"name": "Tipo de descarga",
				"componet": "Tipo de descarga"
			},{
				"id": "070605",
				"name": "Encripción de Archivos",
				"componet": "Encripción de Archivos"
			}]
		},{
			"id": "0707",
			"componet": "Notificaciones Banamex"
		},{
			"id": "0708",
			"componet": "Convertidor de Archivos"
		}]
	}]
};
  if (Object.keys(examples).length > 0) {
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(examples[Object.keys(examples)[0]] || {}, null, 2));
  } else {
    res.end();
  }
}

