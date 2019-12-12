const db = require('db').connect();
const mailer = require('mailer');

module.exports.saveUser = (event, context, callback) => {
  const user = {
    email: event.email,
    created_at: Date.now()
  }

  db.saveUser(user, function (err) {
    if (err) {
      callback(err);
    } else {
      mailer.sendWelcomeEmail(event.email);
      callback();
    }
  });
};
