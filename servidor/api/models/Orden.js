/**
 * Ordenes.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    fecha: {type: "string"},
    total: {type: "number"},
    estado: {type: "string"},
    // ubicacionEntrega:{type: "string"},
    // Ubicacion de Entrega
    latitudEntrega: {type: "number"},
    longitudEntrega: {type: "number"},
    fechaEntrega: {type: "string"},
    costoEntrega: {type: "number"},
  }

};
