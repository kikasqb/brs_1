class Request < ApplicationRecord
  belongs_to :book
  belongs_to :user

  validates :description, presence: true,
    length: {maximum: Settings.max_lenght_request_description}
end
