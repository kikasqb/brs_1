class UserMailer < ApplicationMailer
  def approved_request request
    @request = request
    mail to: request.user.email, subject: I18n.t(:approved_request)
  end

  def notification notifica, user
    @notifica = notifica
    @user = user
    mail to: @user.email, subject: I18n.t(:notification,
      user: notifica.user, new_object: notifica.class.name,
      notifica: notifica.is_a?(Review) ? notifica.book.title : notifica.review.title)
  end
end
