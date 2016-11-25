class UsersController < ApplicationController
  def show
    @user = User.find_by id: params[:id]
    unless @user
      flash[:danger] = t :user_not_found
      redirect_to root_url and return
    end
    if user_signed_in?
      @relationship = current_user.active_relationships
        .find_or_initialize_by followed_id: @user.id
    end
  end
end
