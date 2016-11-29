class Comment < ApplicationRecord
  belongs_to :commentator

  validates :content, presence: true,
    length: {maximum: Settings.max_lenght_content_comment}

  default_scope {where deleted: false}

  delegate :name, to: :commentator, allow_nil: true
  delegate :user, to: :commentator, allow_nil: true
  delegate :title, to: :commentator, allow_nil: true
  delegate :review, to: :commentator, allow_nil: true

  after_create :mail_notifica_other_commentator

  private
  def mail_notifica_other_commentator
    MailerWorker.perform_async MailerWorker::MAIL_NOTIFICATION, id, Comment.name
  end
end
