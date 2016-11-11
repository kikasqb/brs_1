class Book < ApplicationRecord
  belongs_to :category
  has_many :reviews
  has_many :requests

  validates :title, presence: true,
    length: {maximum: Settings.max_lenght_title}
  validates :publish_date, presence: true
  validates :author, presence: true
  validates :number_of_pages, presence: true
  validates :introduce, presence: true,
    length: {maximum: Settings.max_lenght_book_introduce}

  default_scope {where deleted: false}
end
