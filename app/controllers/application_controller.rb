class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception

  def find_user
    @user = User.find_by id: params[:user_id]
    unless @user
      flash[:danger] = t :not_found, objectClass: User.name
      redirect_to root_url and return
    end
  end
end
