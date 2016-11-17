class User < ApplicationRecord
  devise :database_authenticatable, :registerable,
    :recoverable, :rememberable, :trackable, :validatable
  attr_accessor :protected_token
  has_many :activities, dependent: :destroy
  has_many :active_relationships,
    class_name: Relationship.name,
    foreign_key: :follower_id,
    dependent: :destroy
  has_many :following, through: :active_relationships,
    source: :followed
  has_many :passive_relationships,
    class_name: Relationship.name,
    foreign_key: :followed_id,
    dependent: :destroy
  has_many :followers, through: :passive_relationships,
    source: :follower
  has_many :reviews
  has_many :commentators, dependent: :destroy
  has_many :comments, through: :commentators

  VALID_EMAIL_REGEX = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
  validates :name, presence: true, length: {maximum: Settings.max_lenght_name}
  validates :email, presence: true, length: {maximum: Settings.max_lenght_email},
    format: {with: VALID_EMAIL_REGEX},
    uniqueness: {case_sensitive: false}

  validates :password, presence: true,
    length: {minimum: Settings.min_lenght_password,
    maximum: Settings.max_length_password}, allow_nil: true

  mount_uploader :avatar, PictureUploader
end