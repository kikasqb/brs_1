class BooksController < ApplicationController
  load_and_authorize_resource

  def index
    @books = Book.search(params[:key]).of_category(params[:category_id]).page params[:page]
    @all_categories = Category.all
    if user_signed_in?
      @reading_books = current_user.books.reading
      @read_books = current_user.books.read
    end
  end

  def new
    @book = Book.new
  end

  def create
    @book = Book.new book_params
    if @book.save
      flash[:info] = t :created, objectClass: :book, name: @book.title
      redirect_to books_path
    else
      render :new
    end
  end

  def show
    @book = Book.find_by id: params[:id]
    unless @book
      flash[:danger] = t :not_fould, object: Book.name
      redirect_to root_url
    end
  end
end
