class CreateReviews < ActiveRecord::Migration[5.0]
  def change
    create_table :reviews do |t|
      t.references :user, foreign_key: true
      t.references :book, foreign_key: true
      t.string :title
      t.text :content
      t.float :rate
      t.boolean :deleted, default: false

      t.timestamps
    end
  end
end
