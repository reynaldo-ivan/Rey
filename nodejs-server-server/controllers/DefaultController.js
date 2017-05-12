'use strict';

var url = require('url');

var DefaultController = require('./DefaultControllerService');

module.exports.menuPOST = function menuPOST (req, res, next) {
  DefaultController.menuPOST(req.swagger.params, res, next);
};
