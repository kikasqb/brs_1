class Review < ApplicationRecord
  belongs_to :user
  belongs_to :book
  has_many :commentators
  has_many :comments, through: :commentators

  default_scope {where deleted: false}
end
