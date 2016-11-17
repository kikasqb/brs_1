class BooksController < ApplicationController
  before_action :load_book, except: [:new, :index, :create]
  before_action :load_category, only: [:new, :edit, :index]

  def index
    @books = Book.search(params[:key]).of_category(params[:category_id]).page params[:page]
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
  end

  def edit
  end

  def update
    if @book.update_attributes book_params
      flash[:success] = t :updated
      redirect_to @book
    else
      render :edit
    end
  end

  private
  def book_params
    params.require(:book).permit :title, :publish_date, :introduce, :author,
      :number_of_pages, :cover
  end

  def load_book
    @book = Book.find_by id: params[:id]
    unless @book
      flash[:danger] = t :not_fould, object: Book.name
      redirect_to root_url
    end
  end

  def load_category
    @all_categories = Category.all
  end
end
