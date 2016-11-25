class FollowingController < ApplicationController
  before_action :find_user

  def index
    @users = @user.following.search(params[:key]).page(params[:page])
      .per Settings.user.user_per_page
  end
end
