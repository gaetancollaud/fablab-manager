const HtmlWebpackPlugin = require('html-webpack-plugin'); //installed via npm
const webpack = require('webpack'); //to access built-in plugins
const path = require('path');

const config = {
	entry: {
		'fablab': './src/main/js/app.js',
		'styles': './src/main/js/styles.css',
		'vendor-js': './src/main/js/vendors.js',
		'vendor-css': './src/main/js/vendors.css',
	},
	output: {
		path: path.resolve(__dirname, 'dist'),
		filename: '[name]-[hash].min.[ext]'
	},
	module: {
		rules: [
			{
				test: /\.(js)$/,
				use: 'babel-loader'
			},
			{
				test: /\.css$/,
				use: ['style-loader', 'css-loader']
			},
			{
				test: /\.(png|woff|woff2|eot|ttf|svg)$/,
				loader: 'url-loader?limit=100000'
			}
		]
	},
	plugins: [
		// new webpack.optimize.UglifyJsPlugin(),
		new HtmlWebpackPlugin({template: './src/main/js/index.html'})
	]
};

module.exports = config;