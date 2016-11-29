class Admin::UsersController < ApplicationController
  layout "layouts/admin_layout"
  load_and_authorize_resource
  before_action :load_user, only: :destroy

  def index
    @users = User.search(params[:key]).page(params[:page])
      .per Settings.per_page_user
  end

  def destroy
    unless @user.destroy
      flash[:warning] = t :failed, name: @user.name
      redirect_to users
    end
  end

  private
  def user_params
    params.require(:user).permit :name,
      :birthday, :email, :sex, :address,
      :password, :password_confirmation, :avatar
  end

  def load_user
    @user = User.find_by id: params[:id]
    unless @user
      flash[:danger] = t :user_not_found
      redirect_to root_url
    end
  end
end
