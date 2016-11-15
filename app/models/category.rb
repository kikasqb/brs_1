class Category < ApplicationRecord
  has_many :books, dependent: :destroy

  validates :name, presence: true,
    length: {maximum: Settings.max_lenght_name_of_book}

  default_scope {where deleted: false}
end
