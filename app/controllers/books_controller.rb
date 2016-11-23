class BooksController < ApplicationController

  def index
    @books = Book.search(params[:key]).of_category(params[:category_id]).page params[:page]
    @all_categories = Category.all
  end

  def show
    @book = Book.find_by id: params[:id]
    unless @book
      flash[:danger] = t :not_fould, object: Book.name
      redirect_to root_url
    end
  end
end
