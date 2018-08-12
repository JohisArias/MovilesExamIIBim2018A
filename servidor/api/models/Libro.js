/**
 * Libro.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    isbn:{
      type:"number"
    },
    nombre:{
      type:"string"
    },
    nombreEditorial:{
      type:"string"
    },
    precio:{
      type:"string"
    },
    numeroPaginas:{
      type:"number"
    },
    fechaPublicacion:{
      type:"string"
    },

    autorId:{
      type:"number"
    },

  },
};
