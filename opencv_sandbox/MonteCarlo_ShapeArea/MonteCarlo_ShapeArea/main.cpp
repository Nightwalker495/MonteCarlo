#include <cstdlib>
#include <iostream>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace std;

static const cv::String fileName = "rectangle.png";

int main(int argc, char* argv[])
{
	cv::Mat image = cv::imread(fileName, cv::IMREAD_COLOR);
	if (!image.data) {
		cerr << "error: could not open or find the image" << endl;
		return EXIT_FAILURE;
	}

	/*namedWindow("Display window", cv::WINDOW_AUTOSIZE);
	imshow("Display window", image);*/

	cout << "Modifying the image... " << endl;

	for (int row = 0; row < image.rows; row++) {
		for (int col = 0; col < image.cols; col++) {
			cv::Vec3b& color = image.at<cv::Vec3b>(row, col);

			if ((color[0] == 0) && (color[1] == 0) && (color[2] == 0)) {
				color[0] = 30;
				color[1] = 30;
				color[2] = 255;
			}

			/*cv::Vec3b color = image.at<cv::Vec3b>(cv::Point(iterX, iterY));
			
			if ((color[0] == 0) && (color[1] == 0) && (color[2] == 0))
			{
				color[0] = 30;
				color[1] = 30;
				color[2] = 255;

				image.at<cv::Vec3b>(cv::Point(iterX, iterY)) = color;
			}*/
		}
	}

	cout << "Writing image to the file... " << endl;

	cv::imwrite("./rectangle_modified.png", image);

	cv::waitKey(0);

	return EXIT_SUCCESS;
}