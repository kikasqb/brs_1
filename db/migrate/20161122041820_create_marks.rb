class CreateMarks < ActiveRecord::Migration[5.0]
  def change
    create_table :marks do |t|
      t.references :user, foreign_key: true
      t.references :book, foreign_key: true
      t.boolean :read, default: false

      t.timestamps
    end
    add_index :marks, [:user_id, :book_id], unique: true
  end
end
