class FavoritesController < ApplicationController
  load_and_authorize_resource
  before_action :create_messages

  def create
    messages = Hash.new
    if Favorite.find_by book_id: params[:book_id], user_id: current_user.id
      messages[:info] = t :existing_mark_favorite
    else
      @favorite = Favorite.new book_id: params[:book_id], user_id: current_user.id
      if save = favorite.save
        messages[:warning] = t :mark_book_as_favorite, status: t(:success)
      else
        messages[:warning] = t :mark_book_as_favorite, status: t(:failed)
      end
    end
    data = {messages: messages, save: save, favorite: favorite.id }
    respond_to do |format|
      format.json do
        render json: data
      end
    end
  end

  def destroy
    messages = Hash.new
    favorite = Favorite.find_by id: params[:id]
    if favorite
      if deleted = favorite.destroy
        messages[:info] = t :mark_book_as_favorite, status: t(:deleted)
      else
        messages[:danger] = t :mark_book_as_favorite, status: t(:failed_delete)
      end
    else
      flash[:danger] = t :favorite_not_found
      redirect_to books_path
    end
    data = {messages: messages, deleted: deleted}
    respond_to do |format|
      format.json do
        render json: data
      end
    end
  end
end
