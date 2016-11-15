class CreateCommentators < ActiveRecord::Migration[5.0]
  def change
    create_table :commentators do |t|
      t.references :user, foreign_key: true
      t.references :review, foreign_key: true
      t.boolean :deleted, default: false

      t.timestamps
    end
  end
end
