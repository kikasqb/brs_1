class CreateComments < ActiveRecord::Migration[5.0]
  def change
    create_table :comments do |t|
      t.references :commentator, foreign_key: true
      t.text :content
      t.boolean :deleted, default: false

      t.timestamps
    end
  end
end
