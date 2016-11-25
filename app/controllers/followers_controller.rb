class FollowersController < ApplicationController
  before_action :find_user

  def index
    @users = @user.followers.search(params[:key]).page(params[:page])
      .per Settings.user_per_page
  end
end
